import os
import pandas as pd
import numpy as np

data = pd.read_csv(os.path.join('..', 'pre-study_results', 'aggregated', 'numberofbenchmarks.csv'), dtype='str')

numberOf = data['benchmarks'].astype(int)
filter = numberOf[numberOf > 0]
total = 753

print("average: " + str(np.average(filter)))
print("std: " + str(np.std(filter)))
print("median: " + str(np.median(filter)))
print("total: " + str(total))
print("max: " + str(np.max(filter)))

s10 = filter[filter < 10]
print("<10: " + str(s10.size / total))
print("<10: " + str(s10.size))

l50 = filter[filter >= 50]
print(">=50: " + str(l50.size / total))
print(">=50: " + str(l50.size))