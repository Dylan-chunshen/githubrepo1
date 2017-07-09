del ..\easyui-common\gov\easyui-gov-all.js
for /r ../easyui-common/gov/source %%i in (*.js) do type %%i>>../easyui-common/gov/easyui-gov-all.js
java -jar yuicompressor-2.4.2.jar --charset GBK ../easyui-common/gov/easyui-gov-all.js -o ../easyui-common/gov/easyui-gov.js
java -jar yuicompressor-2.4.2.jar --charset GBK ../easyui-common/gov/ValidateFunction.js -o ../easyui-common/gov/ValidateFunction.min.js