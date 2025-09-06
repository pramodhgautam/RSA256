# Generate private key
openssl genrsa -out private.pem 2048

# Extract public key
openssl rsa -in private.pem -pubout -out public.pem


Place private.pem and public.pem inside src/main/resources/keys/.
