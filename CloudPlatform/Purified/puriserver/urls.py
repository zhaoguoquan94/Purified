from django.conf.urls import patterns, url

from puriserver import views
from django.conf import settings
urlpatterns = patterns('',
    # url(r'^$', views.index, name='index'),
    url(r'^repo/$',views.repo,name='topic'),
    url(r'^createTopic/$',views.createTopic),
    url(r'^(?P<topic_id>\d+)/(?P<comment_id>\d+)/good$',views.topicGoodRefresh,name='topicRefresh'),
    url(r'^(?P<topic_id>\d+)/(?P<comment_id>\d+)/bad$',views.topicBadRefresh,name='topicBadRefresh'),
	

    )

# urlpatterns+=patterns('',
 
#     (r'^site_medias/(?P<path>.*)$','django.views.static.serve',
#         {'document_root':settings.STATICFILES_DIRS, 'show_indexes': True}),
# )