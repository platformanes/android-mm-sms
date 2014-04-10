rem @echo off
rem 转到当前盘符
%~d0
rem 打开当前目录
cd %~dp0
rem 你做的主JAR包的路径
set SOURCEJAR=com.mmsns.ane.jar
set MainJar=mmsnsbillingane.jar
rem 第三方JAR包的路径
set ExternalJar=mmsmsbilling1.2.2.jar
rem 第三方JAR包顶级包名称
set packageName=com
set packageName2=mm
rem swc文件名
set SWC=MMANE-SNS.swc
echo =========== start make jar ==============
rem 创建临时目录
md temp
rem 拷贝临时文件
rem copy %SOURCEJAR% %MainJar%
copy .\android-project\bin\%SOURCEJAR% .\temp\%MainJar% >nul
copy .\SDK1.2.2\libs\%ExternalJar% .\temp\ >nul
cd temp
rem 解压第三方包
jar -xf %ExternalJar%
rem 合并主JAR包
jar -uf %MainJar% %packageName% 
rem 合并其他顶级包
jar -uf %MainJar% %packageName2%
rem 拷贝过去ane构建目录
copy %MainJar% ..\ane-build-path\android-ARM >nul
rem 这个本来想打包进ane里面的，不过android太麻烦
xcopy assets\* ..\ /s/q/y >nul
pause
cd ..
rd /s/q temp
echo =========== jar make over,start build ane ==============
copy .\actionscript\bin\%SWC% .\ane-build-path >nul
mkdir .\ane-build-path\android-ARM\libs
mkdir .\ane-build-path\android-ARM\libs\armeabi
mkdir .\ane-build-path\android-ARM\libs\armeabi-v7a
copy .\SDK1.2.2\libs\armeabi\libsmsiap.so .\ane-build-path\android-ARM\libs\armeabi >nul
copy .\SDK1.2.2\libs\armeabi\libsmsiap.so .\ane-build-path\android-ARM\libs\armeabi-v7a >nul
cd ane-build-path
jar -xf %SWC%
move catalog.xml .\android-ARM\ >nul
move library.swf .\android-ARM\ >nul
echo ===========building ane now ===========================
rem build ane
set FLEX_SDK=D:\AIR_3.9
set FLEX_SDK_BIN= %FLEX_SDK%\bin
set FLEX_LIBS=%FLEX_SDK%\frameworks\libs
java -jar "%FLEX_SDK%\lib\adt.jar" -package -target ane com.mmsns.ane extension.xml -swc *.swc -platform Android-ARM -C Android-ARM . 
move com.mmsns.ane ..\ >nul
cd ..
echo =========build complete==========
echo =========打包apk的时候记得把mmiap目录也打包进去，点任意键结束===
pause>nul