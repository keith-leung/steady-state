#!/bin/bash

python3 create_dyredata.py
bash dynamic_reconfiguration.sh
python3 create_dynconfig.py