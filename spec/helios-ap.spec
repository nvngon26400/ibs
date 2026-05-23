# This spec file is for creating RPM.

%define name helios-ap
%define version 1.4.4
%define release 1
%define _binaries_in_noarch_packages_terminate_build 0
%define __jar_repack %{nil}

Name:       %{name}
Version:    %{version}
Release:    %{release}
Summary:    Helios Web Application
License:    Proprietary
Source0:    ifa-portal-ap.war
AutoReqProv: no
BuildArch:  noarch
BuildRoot:	%{_tmppath}/%{name}-%{version}-%{release}-build

# A full description of the software packaged in the RPM. This description can span multiple lines and can be broken into paragraphs.
%description
IFA Portal System(Helios)

# Command or series of commands to prepare the software to be built, for example, unpacking the archive in Source0. This directive can contain a shell script.
%prep
%build

# creating the necessary directories and files in ~/rpmbuild/BUILDROOT for creating a package,
%install
mkdir -p %{buildroot}/opt/apache-tomcat/webapps
unzip -d %{buildroot}/opt/apache-tomcat/webapps/ap -q %{SOURCE0}
%{__rm} -f %{SOURCE0}

# create NAS directory
mkdir -p %{buildroot}/opt/download/pdfTemporary
mkdir -p %{buildroot}/var/app/data/nginx/faq/file
mkdir -p %{buildroot}/var/app/data/nginx/faq/img
mkdir -p %{buildroot}/var/app/data/nginx/faq/video
mkdir -p %{buildroot}/home/ifauser/helios/sugBox/common
mkdir -p %{buildroot}/home/ifauser/helios/sugBox/personal

# cleaning the build root
%clean
%{__rm} -rf %{buildroot}

%post
rm -f /opt/apache-tomcat/webapps/ap.war

# The list of files that will be installed in the end user's system.
%files
%defattr(644,ifauser,ifauser,755)
%dir /opt/apache-tomcat/webapps/ap
/opt/apache-tomcat/webapps/ap/*

%defattr(-,ifauser,ifauser,777)
%dir /opt/download/pdfTemporary

%defattr(-,ifauser,ifauser,755)
%dir /var/app/data/nginx/faq/file
%dir /var/app/data/nginx/faq/img
%dir /var/app/data/nginx/faq/video
%dir /home/ifauser/helios/sugBox/common
%dir /home/ifauser/helios/sugBox/personal

# A record of changes that have happened to the package between different Version or Release builds.
%changelog
