import os
import pandas as pd

data = pd.read_csv(os.path.join('..', 'pre-study_results', 'aggregated', 'jmhversion.csv'), dtype={'version' : 'str'})

total = data['countShortLived'].sum() + data['countLongLived'].sum()

print("extraction possible: " + str(total))