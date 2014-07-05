Purified
========

A service aiming at purified you reading list.
## 与云平台的交互方式

由于采用了csrf方式保护用户免受跨站攻击，我们的每次post都需要先get一个token，用这个token 去post数据

目前提供了这几个form表单，__**每次post都必须按照这个格式post**__否则会验证失败

以下是后台的model，对大家来说只有名字，如LoginForm的username，password是有用的,另外注意一下类型，charfield是字符，boolean需要做成checkbox。
### 每次get和post必须提供cookie！里面必须有两个东西，一个是csrftoken,一个是sessionid
看起来像这样：
csrftoken:eY1KzEIvgMEv3jOIZcQ5kCOWIEbcApRB
sessionid:q7wqz951cwzmf6rlpkbze000jqy3jyn0
### 每次返回信息的时候都要更新这两个域
```
class LoginForm(forms.Form):

	username=forms.CharField(max_length=50)
	password=forms.CharField(max_length=50)
	next=forms.CharField(max_length=50,required=False)
class CreateUserForm(forms.Form):
	username=forms.CharField(max_length=50)
	email=forms.EmailField()
	password=forms.CharField(max_length=50)
	gender=forms.CharField(required=False)
	age=forms.IntegerField(required=False)
class CreateRepo(forms.Form):
	url=forms.CharField(max_length=1024)
	div=forms.CharField(max_length=128)
	content=forms.CharField(max_length=1048576)
	needPushAfterChange=forms.BooleanField()
	keyword=forms.CharField(max_length=128)

	#逗号分隔的categoryID
	category=forms.CharField(max_length=128)
class CreateCategory(forms.Form):
	name=forms.CharField(max_length=128)
	isPublic=forms.BooleanField()
```
url访问资源的规约
r'XXXXX'是正则表达式，表示命中的url指到哪一个处理函数里
### 名字起得很清楚，有问题在issue里提
### 一下是根目录的url
```
    
    url(r'^accounts/login/$', 'puriserver.views.loginView'),
    url(r'^accounts/logout/$', 'puriserver.views.logoutView'),
    url(r'^registration/$','puriserver.views.registrationView'),

    


```
### 以下是根目录下puriserver/的子url


```

	url(r'^repo/$',views.repoView),
	url(r'^repoList/$',views.repoListView),
	url(r'^categoryList/$',views.categoryListView),
	url(r'^category/$',views.categoryView),
	


```
