#!/bin/bash

zip -r code.zip $(ls -A | grep -v "^.git$")
cat <<EOF > email.txt
Hello, Garry.  This is an automated email you requested about Lightning McSteam's code.
I have attached a zip of the robot code to this email for you.

Change information:
EOF
git log -1 >> email.txt
cat <<EOF >> email.txt

Thank you!
EOF
sendemail -l email.log -f "$FROM_EMAIL" -u "Lightning McSteam's Code Update" -t "$TO_EMAIL" -s smtp.gmail.com:587 -o tls=yes -xu "$FROM_EMAIL" -xp "$FROM_PASSWORD" -o message-file=email.txt -a code.zip
