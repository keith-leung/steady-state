library("tidyverse")

path_wd <- getwd()
path_study_results <- paste(path_wd, "..", "study_results", sep = "/")

load_change_rate <- function(path) {
  col_types <- cols(
    `byte-buddy` = col_double(),
    jctools = col_double(),
    jdk = col_double(),
    jenetics = col_double(),
    `jmh-core` = col_double(),
    log4j2 = col_double(),
    protostuff = col_double(),
    rxjava = col_double(),
    squidlib = col_double(),
    zipkin = col_double()
  )
  
  csv <- read_delim(path, del = ";", col_types = col_types) %>%
    gather(key = "project", value = "val") %>%
    drop_na() %>%
    mutate(
      project = case_when(
        project == "byte.buddy" ~ "byte-buddy",
        project == "jmh.core" ~ "jmh-core",
        project == "jctools" ~ "JCToools",
        project == "rxjava" ~ "RxJava",
        project == "squidlib" ~ "SquidLib",
        T ~ project
      )
  )
  
  return(csv)
}

rq_1_change_rate_stats <- function(df) {
  per_proj <- df %>% 
    group_by(project, sc) %>%
    mutate(
      b1 = if_else(val < 0.01, T, F),
      b2 = if_else(val < 0.02, T, F),
      b3 = if_else(val < 0.03, T, F),
      b5 = if_else(val < 0.05, T, F),
      b10 = if_else(val < 0.1, T, F),
    ) %>%
    summarise(
      nr_benchs = n(),
      mean = mean(val),
      median = median(val),
      sd = sd(val),
      iqr = IQR(val),
      b1 = sum(b1)/nr_benchs,
      b2 = sum(b2)/nr_benchs,
      b3 = sum(b3)/nr_benchs,
      b5 = sum(b5)/nr_benchs,
      b10 = sum(b10)/nr_benchs,
    )
  
  all <- df %>%
    group_by(sc) %>%
    mutate(
      b1 = if_else(val < 0.01, T, F),
      b2 = if_else(val < 0.02, T, F),
      b3 = if_else(val < 0.03, T, F),
      b5 = if_else(val < 0.05, T, F),
      b10 = if_else(val < 0.1, T, F),
    ) %>%
    summarise(
      nr_benchs = n(),
      mean = mean(val),
      median = median(val),
      sd = sd(val),
      iqr = IQR(val),
      b1 = sum(b1)/nr_benchs,
      b2 = sum(b2)/nr_benchs,
      b3 = sum(b3)/nr_benchs,
      b5 = sum(b5)/nr_benchs,
      b10 = sum(b10)/nr_benchs,
    )
  print(all)
}

rq_1_change_rate_plots <- function(df, out_path) {
  p <- ggplot(df, aes(x = project, y = val, fill = sc)) +
    geom_boxplot() +
    theme_bw() +
    theme(
      legend.position = "top",
      legend.title=element_text(size=18),
      legend.text = element_text(size = 18),
      axis.text = element_text(size = 18),
      axis.text.x = element_text(angle = 20, hjust = 1),
      axis.title = element_text(size = 20, face = "bold")
    ) +
    scale_fill_manual(name = "Stoppage Criteria", values=c("#f1a340", "#f7f7f7", "#998ec3")) +
    stat_summary(fun.y = mean, geom = "point", shape = 23, size = 3, position = position_dodge(width = .75), show.legend = F) +
    scale_y_continuous(labels = scales::percent_format(accuracy = 1L)) +
    coord_cartesian(ylim = c(0, 0.12)) +
    xlab("Study Subject") +
    ylab("Change Rate")
  
  
  ggsave(paste(out_path, "rq_1_change_rate.pdf", sep = "/"), p, width = 10, height = 6)
}

rq_1_change_rate_all <- function() {
  path <- paste(path_study_results, "variability", sep = "/")
  
  change_rate_cv <- load_change_rate(paste(path, "boxplot_meanchangerate_cov.csv", sep = "/")) %>%
    mutate(sc = "CV")
  
  change_rate_rciw <- load_change_rate(paste(path, "boxplot_meanchangerate_ci.csv", sep = "/")) %>%
    mutate(sc = "RCIW")

  change_rate_kld <- load_change_rate(paste(path, "boxplot_meanchangerate_divergence.csv", sep = "/")) %>%
    mutate(sc = "KLD")

  
  change_rate_all <- rbind(change_rate_cv, change_rate_rciw, change_rate_kld)
  
  rq_1_change_rate_stats(change_rate_all)
  
  rq_1_change_rate_plots(change_rate_all, path_wd)
}

