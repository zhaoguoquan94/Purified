from django.conf.urls import patterns, url
from puriserver import views
from django.conf import settings
urlpatterns = patterns('',
    # url(r'^$', views.index, name='index'),
    url(r'^repo/$',views.repoView),
    url(r'^repoList/$',views.repoListView),
    url(r'^categoryList/$',views.categoryListView),
    url(r'^category/$',views.categoryView),
    

    )

# urlpatterns+=patterns('',
 
#     (r'^site_medias/(?P<path>.*)$','django.views.static.serve',
#         {'document_root':settings.STATICFILES_DIRS, 'show_indexes': True}),
# )