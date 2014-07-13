from django.db import models
from django.utils import timezone
from django.contrib.auth.models import User
# Create your models here.
class PURepo(models.Model):
    user=models.ForeignKey(User)
    url=models.CharField(max_length=1024)
    div=models.CharField(max_length=128)
    content=models.TextField(max_length=1048576)
    needPushAfterChange=models.BooleanField(default=False)
    keyword=models.CharField(max_length=128)
    category=models.ManyToManyField("PUCategory")
    
    lastUpdateDate=models.DateField(auto_now=True)
    def __unicode__(self):
        return str(self.user)+self.url

class PUCategory(models.Model):
    name=models.CharField(max_length=128)
    #fatherCategory
    user=models.ForeignKey(User)

    #0    private
    #1  friends
    #2    public
    isPublic=models.IntegerField(default=0)
    def __unicode__(self):
        return self.name



class PUUserProfile(models.Model):
    user=models.OneToOneField(User)
    age=models.IntegerField(default=18)
    gender=models.CharField(max_length=10)
    friend=models.ManyToManyField("PUUserProfile",blank=True)
