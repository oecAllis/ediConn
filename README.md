# Purpose
This project aims to address EDI Ansi 315 to receive and send transaction to 3rd parties.

## Branches
There are three branches for which you should concern yourself with:
* master: maintains release build
* dev: maintains current development
* feature: maintains specific feature fea-01

## Environment
Code formatting uses intellij-java-goolgle-style.xml and editor uses Intellij CE version
JDK version at least 1.11

## Functions
This module includes three parts. The fundamental setting and project library is controlled by Allis. 

- 315 edi parser
- ftp folder scan and archive: sftp client sample is in conn folder. 
enter ftp entry, ls to find out vendor lists. find out how many edi files that need to be handled. 
for each vendor, cd vendor/Inbound/EDI315, ls to get edi file lists for this vendor.
archive all edi files in that folder and download it to local, extract.
If all edi files in that folder handled properly, check whether edi files has changed.
if not, move archive to archived folder and delete all
If additional one has added, then keep it and remove remaining.
- different format in-out (fixed length and edi/xml output): fixed length see parser/text folder

## Package Structure
- conn: connectivity such as sftp, etc
- integration: repo defines repository, model define entity
- parser: edi for general and 315 spec, text for plain text with fixed length, util for common reused functions

## Deploy
execute commands
```
~/code/ediConn/ &
./gradlew clean &
./gradlew -Psit distTar &
cd build/distributions/ &
scp -r -P 22 ediConn-1.0-SNAPSHO.tar unify@uc:~/
ssh unify@uc in sit
mkdir ediFtp &
mkdir railFtp &
tar -xvf ediConn-1.0-SNAPSHO.tar &
cd ediConn-1.0-SNAPSHO/bin/ &
./ediConn
```
