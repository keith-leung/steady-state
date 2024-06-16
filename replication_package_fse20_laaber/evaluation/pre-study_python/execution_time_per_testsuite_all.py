import os
import pandas as pd
import numpy as np

data = pd.read_csv(os.path.join('..', 'pre-study_results', 'aggregated', 'executiontime.csv'))
data['totalTime'] = data['executionTime'] * data['parameterizationCombinations'] / 60

grouped = data.groupby('project')

nrAllProjects = 753

array = []
for project, benchmarks in grouped:
    array.append(benchmarks['totalTime'].sum() / 60)

all, base = np.histogram(array, bins=1000, range=[0, 12], weights=np.ones(len(array)) / len(array))
cumulative = np.cumsum(all)

print("all times are in hours")
print("min: " + str(np.min(array)))
print("max: " + str(np.max(array)))
print("median: " + str(np.median(array)))

print("total projects: " + str(len(array)))

s1 = list(filter(lambda x: x < 1, array))
print("<1h (total): " + str(len(s1)))
print("<1h (relative to " + str(len(array)) + "): " + str(len(s1) / len(array)))
print("<1h (relative to " + str(nrAllProjects) + "): " + str(len(s1) / nrAllProjects))

l3 = list(filter(lambda x: x > 3, array))
print(">3h (total): " + str(len(l3)))
print(">3h (relative to " + str(len(array)) + "): " + str(len(l3) / len(array)))
print(">3h (relative to " + str(nrAllProjects) + "): " + str(len(l3) / nrAllProjects))

l12 = list(filter(lambda x: x > 12, array))
print(">12h (total): " + str(len(l12)))
print(">12h (relative to " + str(len(array)) + "): " + str(len(l12) / len(array)))
print(">12h (relative to " + str(nrAllProjects) + "): " + str(len(l12) / nrAllProjects))
