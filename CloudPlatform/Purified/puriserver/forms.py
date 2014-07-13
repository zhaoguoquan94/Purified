# -*- coding: utf-8 -*-

# from datetime import datetime

from django import forms
# from models import *
class LoginForm(forms.Form):

    username=forms.CharField(max_length=50)
    # email=forms.EmailField()
    password=forms.CharField(max_length=50)
    next=forms.CharField(max_length=50,required=False)
class CreateUserForm(forms.Form):
    username=forms.CharField(max_length=50)
    email=forms.EmailField()
    password=forms.CharField(max_length=50)
    gender=forms.CharField(required=False)
    age=forms.IntegerField(required=False)
class CreateRepoForm(forms.Form):
    url=forms.CharField(max_length=1024)
    div=forms.CharField(max_length=128,required=False)
    content=forms.CharField(max_length=1048576)
    needPushAfterChange=forms.BooleanField()
    keyword=forms.CharField(max_length=128)

#逗号分隔的categoryID
    category=forms.CharField(max_length=128)
class CreateCategoryForm(forms.Form):
    name=forms.CharField(max_length=128)
    isPublic=forms.BooleanField()