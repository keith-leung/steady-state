import os
import pandas as pd
import numpy as np

data = pd.read_csv(os.path.join('..', 'pre-study_results', 'current-commit', 'merged-isMain-header.csv'))

grouped = data.groupby('project')

array = []
for project, benchmarks in grouped:
    array.append(benchmarks['parameterizationCombinations'].sum())

total = 753

print("average: " + str(np.average(array)))
print("std: " + str(np.std(array)))
print("median: " + str(np.median(array)))
print("max: " + str(np.max(array)))
print("total: " + str(total))