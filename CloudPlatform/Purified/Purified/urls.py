from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'Purified.views.home', name='home'),
    # url(r'^Purified/', include('Purified.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    url(r'^puriserver/',include('puriserver.urls',namespace='puriserver'),name='puriserver'),
    url(r'^accounts/login/$', 'puriserver.views.loginView'),
    url(r'^accounts/logout/$', 'puriserver.views.logoutView'),
    # url(r'^registration/$','puriserver.views.registrationView'),
    url(r'^admin/', include(admin.site.urls)),
)
