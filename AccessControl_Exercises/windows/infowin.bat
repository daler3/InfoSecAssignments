@echo off
:: BatchGotAdmin (Run as Admin code starts)
REM --> Check for permissions
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
REM --> If error flag set, we do not have admin.
if '%errorlevel%' NEQ '0' (
echo Requesting administrative privileges...
goto UACPrompt
) else ( goto gotAdmin )
:UACPrompt
echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
echo UAC.ShellExecute "%~s0", "", "", "runas", 1 >> "%temp%\getadmin.vbs"
"%temp%\getadmin.vbs"
exit /B
:gotAdmin
if exist "%temp%\getadmin.vbs" ( del "%temp%\getadmin.vbs" )
pushd "%CD%"
CD /D "%~dp0"
:: BatchGotAdmin (Run as Admin code ends)

net localgroup project2016 /add
net localgroup project2016 %USER% /add
net user testuser testuser /add
net localgroup project2016 testuser /add
CD\
mkdir assignment
CD assignment
copy NUL EmptyFile.txt
mkdir confidential
CD confidential
copy NUL EmptyFileConf.txt

icacls C:\assignment\confidential\EmptyFileConf.txt /inheritance:r
icacls C:\assignment\confidential\EmptyFileConf.txt /grant %USERNAME%:f 

icacls C:\assignment\confidential\ /inheritance:r
icacls C:\assignment\confidential /grant %USERNAME%:f 

icacls C:\assignment\EmptyFile.txt /inheritance:r
icacls C:\assignment\EmptyFile.txt /grant project2016:f 

icacls C:\assignment\ /inheritance:r
icacls C:\assignment /grant project2016:f 