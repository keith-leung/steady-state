library("tidyverse")

path_wd <- getwd()
path_prestudy_results <- paste(path_wd, "..", "pre-study_results", sep = "/")

load_execution_time <- function(path) {
  col_types <- cols(
    project = col_character(),
    className = col_character(),
    jmhVersion = col_character(),
    benchmarkName = col_character(),
    executionTimeDefault = col_double(),
    executionTime = col_double(),
    executionTimePercentage = col_double(),
    warmupTimeDefault = col_double(),
    warmupTime = col_double(),
    warmupTimePercentage = col_double(),
    measurementTimeDefault = col_double(),
    measurementTime = col_double(),
    measurementTimePercentage = col_double(),
    onlyModeChanged = col_logical(),
    onlySingleShot = col_logical(),
    measurementWarmupRatio = col_double(),
    measurementWarmupRatioPerMeasurementFork = col_double(),
    hasWarmupForks = col_logical(),
    parameterizationCombinations = col_integer()
  )
  
  csv <- read_delim(path, del = ",", col_types = col_types)
  
  return(csv)
}

prestudy_suite_execution_time <- function(df, out_path) {
  # projects
  total <- 753
  
  df_set <- df %>%
    mutate(totalTime = executionTime * parameterizationCombinations / 60 / 60) %>%
    group_by(project) %>%
    summarise(time = sum(totalTime)) %>%
    ungroup() %>%
    arrange(time) %>%
    mutate(cumproj = row_number())
  
  p <- ggplot(df_set, aes(x = time, y = cumproj)) +
    geom_line(size = 1) +
    theme_bw() +
    theme(
      legend.title=element_text(size=18),
      legend.text = element_text(size = 18),
      axis.text = element_text(size = 18),
      axis.title = element_text(size = 20, face = "bold")
    ) +
    coord_cartesian(xlim = c(0, 12)) +
    scale_y_continuous(
      breaks = c(seq(0, 600, 100)),
      sec.axis = sec_axis(
        ~ . / total,
        labels = scales::percent_format(accuracy = 1L),
        breaks = c(seq(0, 1, 0.1), 0.76)
      )
    ) +
    scale_x_continuous(breaks = seq(0, 12, 1)) +
    ylab("# Projects (cum)") +
    xlab("Execution Time [h]")
  
  ggsave(paste(out_path, "prestudy_suite_execution_time.pdf", sep = "/"), p, width = 7, height = 5)
}

prestudy_reduced_stats <- function(df, total) {
  facstats <- df %>%
    mutate(
      f2 = if_else(etfac >= 2, T, F),
      f3 = if_else(etfac >= 3, T, F),
      f5 = if_else(etfac >= 5, T, F),
      f7 = if_else(etfac >= 7, T, F),
      f10 = if_else(etfac >= 10, T, F),
      f15 = if_else(etfac >= 15, T, F),
      f25 = if_else(etfac >= 25, T, F),
      f50 = if_else(etfac >= 50, T, F),
      f100 = if_else(etfac >= 100, T, F),
    ) %>%
    summarise(
      nr_benchs = n(),
      mean = mean(etfac),
      median = median(etfac),
      sd = sd(etfac),
      iqr = IQR(etfac),
      f2t = sum(f2),
      f2p = f2t/total,
      f3t = sum(f3),
      f3p = f3t/total,
      f5t = sum(f5),
      f5p = f5t/total,
      f7t = sum(f7),
      f7p = f7t/total,
      f10t = sum(f10),
      f10p = f10t/total,
      f15t = sum(f15),
      f15p = f15t/total,
      f25t = sum(f25),
      f25p = f25t/total,
      f50t = sum(f50),
      f50p = f50t/total,
      f100t = sum(f100),
      f100p = f100t/total,
    )
  
  print(facstats)
}

prestudy_reduced_plot <- function(df, total, out_path) {
  p <- ggplot(df, aes(x = etfac, y = cumbench)) +
    geom_line(size = 1) +
    theme_bw() +
    theme(
      legend.title=element_text(size=18),
      legend.text = element_text(size = 18),
      axis.text = element_text(size = 18),
      axis.title = element_text(size = 20, face = "bold")
    ) +
    coord_cartesian(xlim = c(1, 100)) +
    scale_y_continuous(
      breaks = c(seq(0, 4500, 500)), 
      sec.axis = sec_axis(
        ~ . / total,
        labels = scales::percent_format(accuracy = 1L),
        breaks = c(seq(0, 0.3, 0.05), 0.34)
      )
    ) +
    scale_x_continuous(breaks = c(1, 2, 3, 5, 7, 10, 15, 25, 50, 100), trans = "log10") + #seq(0, 100, 10)
    ylab("# Benchmarks (cum)") +
    xlab(bquote(bold(paste("Decrease Factor (", log[10], ")", sep = ""))))
  
  ggsave(paste(out_path, "prestudy_decrease_factor.pdf", sep = "/"), p, width = 7, height = 5)
}

prestudy_reduced <- function(df, out_path) {
  df_r <- df %>%
    filter(onlyModeChanged == F & onlySingleShot == F & executionTimePercentage < 1) %>%
    mutate(etfac = executionTimeDefault / executionTime) %>%
    arrange(etfac) %>%
    mutate(cumbench = row_number())
  
  # benchmarks
  total <- 13387
  
  prestudy_reduced_stats(df_r, total)
  prestudy_reduced_plot(df_r, total, out_path)
}

prestudy_execution_time_all <- function() {
  path <- paste(path_prestudy_results, "aggregated", sep = "/")
  
  df_execution_time <- load_execution_time(paste(path, "executiontime.csv", sep = "/"))
  
  prestudy_suite_execution_time(df_execution_time, path_wd)
  prestudy_reduced(df_execution_time, path_wd)
}
