#Script
#
#
cd /home
mkdir /home/assignment
cd assignment 
touch textfile.txt
mkdir /home/assignment/confidential
cd confidential
touch textfileconf.txt

groupadd project2016
usermod -G project2016 $USER
useradd -G project2016 testuser

chown $USER /home/assignment -R
chmod 770 /home/assignment -R
chmod 700 /home/assignment/confidential -R

 
