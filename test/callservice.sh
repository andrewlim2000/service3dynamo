#!/bin/bash

json={"\"table\"":"\"1000000SalesRecords_processed\"","\"region\"":"\"Australia\u0020and\u0020Oceania\"","\"itemType\"":"\"Office\u0020Supplies\"","\"salesChannel\"":"\"Offline\"","\"orderPriority\"":"\"Medium\"","\"country\"":"\"Fiji\""}

echo "Invoking Lambda function using AWS CLI"
time output=`aws lambda invoke --invocation-type RequestResponse --function-name service3dynamo --region us-east-2 --payload $json /dev/stdout | head -n 1 | head -c -2 ; echo`

echo ""
echo "JSON RESULT:"
echo $output | jq
echo ""
