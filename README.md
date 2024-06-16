# steady-state
A reproduction of https://github.com/SEALABQualityGroup/steady-state

1. The jmh.tar.gz from https://zenodo.org/records/5961018 and the dynamic reconfiguration data from https://figshare.com/articles/dataset/Replication_Package_Dynamically_Reconfiguring_Software_Microbenchmarks_Reducing_Execution_Time_Without_Sacrificing_Result_Quality_/11944875 are omitted to reduce the repo's size. But I keep the bencher's code change in the directory replication_package_fse20_laaber/bencher. 
2. The json files in data are omitted because they are not GitHub-friendly. They can be downloaded with the instructions in the original GitHub project.
3. All the figures and csv results are here for verification. 

Since the figures being uploaded to GitHub will lose the original modification datetime, I use a figure.zip to store all the figure PDF **keeping the original modification datetime when they were generated**.

Found issues: 
- To build the bencher in dynamic reconfiguration, it compiles to a 0.5.0 JAR file. When the project runs, it requires 1.0-SNAPSHOT.jar in local maven repository.
- Some script names are mismatched with the README.md.
- To run the python analysis script, you should chdir to the directory analysis.
- Don't use Python 3.6, because its compatible highest version of numpy is 1.19.5, but the code requires **sliding_window_view** which is newly introduced in 1.20.0.
- Some libs in requirements.txt with their versions are not able to install. I have to modify them to requirements_no_version.txt.
- (sorry, I don't remember other issues that I figured out during the reproduction process.)

