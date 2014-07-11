# Create your views here.
# -*- coding: utf-8 -*-from blog.models import *
from django.template import Context, loader
from django.http import Http404
from django.shortcuts import get_object_or_404, render
from django.http import HttpResponseRedirect, HttpResponse
from django.core.urlresolvers import reverse
# from forum.models import Topic,Comment,ImageInTopic,StaffComment
from django.utils import timezone
from puriserver.forms import *
import datetime
from django.shortcuts import render_to_response
from django.template import RequestContext
# from PIL import Image
import StringIO
from django.contrib.auth.models import User 
# from django.contrib.auth.models import AnonymousUser
import urllib
# from django.core.serializers.json import DjangoJSONEncoder
import json
import base64
from django.contrib.auth import logout,login
from django.contrib.auth import authenticate
timeFormat="%Y-%m-%d %H:%M:%S"

def loginView(request):
    print "login view called"
    if request.method=="POST":
        form = LoginForm(request.POST)
        if form.is_valid():
            print "form valid"
            cd = form.cleaned_data
            username=cd['username']
            password=cd['password']
            user = authenticate(username=username, password=password)

            if user is not None:
                print "user valid"
                login(request,user)
                return HttpResponse("login success")
            else:
                print "wrong password"
                # print ans
                return HttpResponse("wrong password")
        else:
            print "form not valid"
            return HttpResponse("form not valid")
    else:
        print "return login page"
        # next=request.GET['next']
        return render_to_response("puriserver/login.html",{},context_instance=RequestContext(request))

def logoutView(request):
    print "logout"
    logout(request)
    return HttpResponse("logout success")


def repoView(request):
    #post方法用于上传repo，get方法用于得到token,sessionid
    if (request.method=="POST"):
        if (request.user.is_authenticated()):
            form=CreateRepo(request.POST)
            if form.is_valid:
                cd=form.cleaned_data
                newRepo=PURepo(
                    user=request.user,
                    url=cd['url'],
                    div=cd['div'],
                    content=cd['content'],
                    needPushAfterChange=cd['needPushAfterChange'],
                    keyword=cd['keyword'],
                    lastUpdateDate=datetime.datetime.now(),
                    )
                categorys=cd['category'].split(",")
                for item in categorys:
                    newRepo.category_set.add(PUCategory.objects.get(pk=int(item)))


                newCate.save()
                return HttpResponse("success")
            else:
                #form not valid
                return HttpResponse("error,form not valid")



    else:
        #返回token
        return render_to_response("puriserver/login.html",context_instance=RequestContext(request))


def repoListView(request):
    """用于获取url以及内容最近更改时间，客户端发现更新则下载最新内容"""
    if request.user.is_authenticated():
        #user 已经认证可以传输信息
        repoList=PURepo.objects.filter(user=request.user)
        dicList=[]
        for i in range(0,len(repoList)):
            dicList.append({
                'url':repoList[i].url,
                'lastUpdateDate':repoList[i].lastUpdateDate.strftime(timeFormat)
                })


        dic={'repos':dicList}
        jsonDic=json.dumps(dic)
        return HttpResponse(jsonDic)
    else:
        #user 未登录，do nothing
        return HttpResponse("user not authenticate")
def categoryListView(request):
    """get获取所有Category"""
    if request.user.is_authenticated():
        cateList=PUCategory.objects.filter(user=request.user)
        dicList=[]
        for i in range(0,len(cateList)):
            dicList.append({
                'id':cateList[i].id,
                'name':cateList[i].name,
                'isPublic':categoryList[i].isPublic
                })
        dic={'categorys':dicList}
        jsonDic=josn.dumps(dic)
        return HttpResponse(jsonDic)
def categoryView(request):
    #post方法用于上传cate，get方法用于得到token
    if (request.method=="POST"):
        if request.user.is_authenticated():
            form=CreateCategory(request.POST)
            if form.is_valid:
                cd=form.cleaned_data
                newCate=PUCategory(
                    name=cd['name'],
                    user=request.user,
                    isPublic=cd['isPublic']

                    )
                newCate.save()
                return HttpResponse("success")
            else:
                #form not valid
                return HttpResponse("error,form not valid")
    else:
        #返回token
        return render_to_response("puriserver/login.html",context_instance=RequestContext(request))
