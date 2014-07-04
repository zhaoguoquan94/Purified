# Create your views here.
# -*- coding: utf-8 -*-from blog.models import *
from django.template import Context, loader
from django.http import Http404
from django.shortcuts import get_object_or_404, render
from django.http import HttpResponseRedirect, HttpResponse
from django.core.urlresolvers import reverse
from forum.models import Topic,Comment,ImageInTopic,StaffComment
from django.utils import timezone
from forum.forms import CommentForm,TopicForm,LoginForm
import datetime
from django.shortcuts import render_to_response
from django.template import RequestContext
from PIL import Image
import StringIO
from django.contrib.auth.models import User 
from django.contrib.auth.models import AnonymousUser
import urllib
# from django.core.serializers.json import DjangoJSONEncoder
import json
import base64
from django.contrib.auth import logout,login
from django.contrib.auth import authenticate

def loginView(request):
    print "login view called"
    if request.method=="POST":
        form = LoginForm(request.POST)
        if form.is_valid():
            print "form valid"
            cd = form.cleaned_data
            username=cd['username']
            password=cd['password']
            data={'uuid':username,'password':password}
            data=urllib.urlencode(data).encode()
            authURL='http://114.215.202.154/api/login/'
            res=urllib.urlopen(authURL,data=data)
            ans=json.loads(res.read().decode('utf-8'),encoding='utf-8')
            if ans['code']==200:
                print "login success"
                username=ans['user']['uuid']
                password=base64.b64encode(ans['user']['email'])
                user = authenticate(username=username, password=password)

                if user is not None:
                    print "user valid"
                    login(request,user)
                    return HttpResponseRedirect("/forum")
                else:
                    user=User.objects.create_user(username=username,password=password)
                    user.save()
                    login(request,user)
                    return HttpResponseRedirect("/forum")
                    # return HttpResponse("/forum")
            else:
                print "wrong password"
                print ans
                return render_to_response("registration/login.html",{"errors":['wrong password']},context_instance=RequestContext(request))
        else:
            print "form not valid"
            return render_to_response("registration/login.html",{"errors":['form not valid']},context_instance=RequestContext(request))
    else:
        print "return login page"
        # next=request.GET['next']
        return render_to_response("registration/login.html",{},context_instance=RequestContext(request))

def logoutView(request):
    print "logout"
    logout(request)
    return HttpResponseRedirect("/forum")


def repoView(request):
	#post方法用于上传repo，get方法用于得到token,sessionid
	if (request.method=="POST"):

	else:
		#返回token





def repoListView(request):
	"""用于获取url以及内容最近更改时间，客户端发现更新则下载最新内容"""
	if request.method=="POST":
		#得到所有repo精简信息
	else:
		#得到token







