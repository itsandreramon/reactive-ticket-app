#!/bin/bash
openssl enc -d -aes-256-cbc -k $KEY -in release/google-services.json.encrypted -out app/google-services.json