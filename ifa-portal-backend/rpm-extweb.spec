# This spec file is for creating RPM.

#If you want to comment out a line with a macro, double the percent signs (%%) as in the following example:
#%%define _source_payload w0.gzdio
#%%define _binary_payload w0.gzdio

#Stop to call brp-java-repack-jars, keeping original jar files.
#%%define __jar_repack %{nil}

#You must modify the user name of the authority to belong to the current system.
#The current example is the user rights of the ceres system.
%define app_name cbcommon-web
%define auth_user ceres
%define out_path /opt/ceres/http

Name:       %{app_name}
Version:    0.5.0
Release:    1
Summary:    CB Common Web Application
License:    Proprietary
URL:        www.sbisec.co.jp
Source0:    %{app_name}
AutoReqProv: no
BuildArch:  noarch
BuildRoot:  %{_tmppath}/%{name}-%{version}-%{release}-build

# A full description of the software packaged in the RPM. This description can span multiple lines and can be broken into paragraphs.
%description
Lending Pricing System

# Command or series of commands to prepare the software to be built, for example, unpacking the archive in Source0. This directive can contain a shell script.
%prep
%{__rm} -rf  %{buildroot}

# creating the necessary directories and files in ~/rpmbuild/BUILDROOT for creating a package,
%install
mkdir -p %{buildroot}%{out_path}
cp -r %{SOURCE0}/* %{buildroot}%{out_path}
%{__rm} -rf %{SOURCE0}

# cleaning the build root
%clean
%{__rm} -rf %{buildroot}

# The list of files that will be installed in the end user’s system.
%files
%defattr(644,%{auth_user},%{auth_user},755)
%dir %{out_path}
%{out_path}/*

# A record of changes that have happened to the package between different Version or Release builds.
%changelog
