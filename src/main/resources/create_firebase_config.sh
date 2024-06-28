#!/bin/sh

# Load environment variables from .env file
set -o allexport
source /app/.env
set -o allexport

cat <<EOF > /app/src/main/resources/firebase-admin-sdk.json
{
  "type": "$FIREBASE_TYPE",
  "project_id": "$FIREBASE_PROJECT_ID",
  "private_key_id": "$FIREBASE_PRIVATE_KEY_ID",
  "private_key": "$FIREBASE_PRIVATE_KEY",
  "client_email": "$FIREBASE_CLIENT_EMAIL",
  "client_id": "$FIREBASE_CLIENT_ID",
  "auth_uri": "$FIREBASE_AUTH_URI",
  "token_uri": "$FIREBASE_TOKEN_URI",
  "auth_provider_x509_cert_url": "$FIREBASE_AUTH_PROVIDER_X509_CERT_URL",
  "client_x509_cert_url": "$FIREBASE_CLIENT_X509_CERT_URL",
  "universe_domain": "$FIREBASE_UNIVERSE_DOMAIN"
}
EOF
