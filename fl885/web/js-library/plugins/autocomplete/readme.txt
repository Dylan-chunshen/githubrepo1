�ο���ַ��http://www.jb51.net/article/24219.htm

1��jquery.autocomplete�ο���ַ 
http://bassistance.de/jquery-plugins/jquery-plugin-autocomplete/ 
http://docs.jquery.com/Plugins/Autocomplete 
2��jquery.autocomplete��� 
�﷨�� 
autocomplete(urlor data, [options] ) 
������ 
url or data���������url 
[options]����ѡ�ѡ��������£� 
1) minChars (Number) 
�ڴ���autoCompleteǰ�û�������Ҫ������ַ�����Default:1�������Ϊ0�����������˫������ɾ�������������ʱ��ʾ�б� 
2) width (Number) 
ָ��������Ŀ�ȣ�Default: inputԪ�صĿ�� 
3) max (Number) 
autoComplete������ʾ��Ŀ�ĸ�����Default: 10 
4) delay (Number) 
�����󼤻�autoComplete���ӳ�ʱ��(��λ����)��Default: Զ��Ϊ400 ����10 
5) autoFill (Boolean) 
Ҫ��Ҫ���û�ѡ��ʱ�Զ����û���ǰ������ڵ�ֵ���뵽input��Default: false 
6) mustMatch (Booolean) 
�������Ϊtrue��autoCompleteֻ������ƥ��Ľ�������������,���е��û�������ǷǷ��ַ�ʱ����ò���������Default:false 
7) matchContains (Boolean) 
�����Ƚ�ʱ�Ƿ�Ҫ���ַ����ڲ��鿴ƥ�䣬��ba�Ƿ���foo bar�е�baƥ��.ʹ�û���ʱ�Ƚ���Ҫ.��Ҫ��autofill����.Default: false 
8) selectFirst (Boolean) 
������ó�true,���û�����tab��return��ʱautoComplete�����б�ĵ�һ��ֵ�����Զ�ѡ��,������û���ֹ�ѡ��(�ü��̻����).��Ȼ����û�ѡ��ĳ����Ŀ,��ô�����û�ѡ�е�ֵ. Default: true 
9) cacheLength (Number) 
����ĳ���.���Դ����ݿ���ȡ���Ľ����Ҫ�����������¼.���1Ϊ������.Default: 10 
10) matchSubset (Boolean) 
autoComplete�ɲ�����ʹ�öԷ�������ѯ�Ļ���,��������foo�Ĳ�ѯ���,��ô����û�����foo�Ͳ���Ҫ�ٽ��м�����,ֱ��ʹ�û���.ͨ���Ǵ����ѡ���Լ���������ĸ������������.ֻ���ڻ��泤�ȴ���1ʱ��Ч.Default: true 
11) matchCase (Boolean) 
�Ƚ��Ƿ�����Сд���п���.ʹ�û���ʱ�Ƚ���Ҫ.����������һ��ѡ��,���Ҳ�Ͳ������,�ͺñ�footҪ��Ҫ��FOO�Ļ�����ȥ��.Default: false 
12) multiple (Boolean) 
�Ƿ�����������ֵ�����ʹ��autoComplete��������ֵ. Default:false 
13) multipleSeparator (String) 
����Ƕ�ѡʱ,�����ֿ�����ѡ����ַ�. Default:"," 
14) scroll (Boolean) 
�����������Ĭ�ϸ߶�ʱ�Ƿ�ʹ�þ�����ʾ Default: true 
15) scrollHeight (Number) 
�Զ������ʾ�ľ���߶������ش�С��ʾ Default: 180 
16) formatItem (Function) 
Ϊÿ��Ҫ��ʾ����Ŀʹ�ø߼���ǩ.���Խ���е�ÿһ�ж�������������,����ֵ����LIԪ�ذ�����ʾ�������б���.Autocompleter���ṩ��������(row, i, max): ���صĽ������, ��ǰ���������(���ڼ�����Ŀ,�Ǵ�1��ʼ����Ȼ��), ��ǰ�������Ԫ�صĸ�������Ŀ�ĸ���.Default: none, ��ʾ��ָ���Զ���Ĵ�����,���������б��е�ÿһ��ֻ����һ��ֵ. 
17) formatResult (Function) 
��formatItem����,�����Խ���Ҫ���뵽input�ı����ڵ�ֵ���и�ʽ��.ͬ������������,��formatItemһ��.Default: none,��ʾҪô��ֻ������,Ҫô��ʹ��formatItem�ṩ��ֵ. 
18) formatMatch (Function) 
��ÿһ������ʹ�ô˺�����ʽ����Ҫ��ѯ�����ݸ�ʽ. ����ֵ�Ǹ��ڲ������㷨ʹ�õ�. ����ֵrow 
19) extraParams (Object) 
Ϊ��̨(һ���Ƿ���˵Ľű�)�ṩ����Ĳ���.��ͨ��������һ����ʹ��һ����ֵ�Զ���.�������ȥ��ֵ��{ bar:4 },���ᱻautocompleter������my_autocomplete_backend.php?q=foo&bar=4 (���赱ǰ�û�������foo). Default: {} 
20) result (handler) Returns:jQuery 
���¼������û�ѡ��ĳһ��󴥷�������Ϊ�� 
event: �¼�����. event.typeΪresult. 
data: ѡ�е�������. 
formatted:formatResult�������ص�ֵ 
���磺 
$("#singleBirdRemote").result(function(event, data, formatted){ 
//��ѡ���������ؼ���ֵ����������¼��ȵ� 
}); 