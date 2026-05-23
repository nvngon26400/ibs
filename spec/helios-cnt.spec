# This spec file is for creating RPM.

%define name helios-cnt
%define version 1.4.4
%define release 1
%define _binaries_in_noarch_packages_terminate_build 0
%define __jar_repack %{nil}

Name:       %{name}
Version:    %{version}
Release:    %{release}
Summary:    Helios Content
License:    Proprietary
Source0:    dist
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
mkdir -p %{buildroot}/var/app/data/nginx/html/ui
cp -rp %{SOURCE0}/* %{buildroot}/var/app/data/nginx/html/ui/
%{__rm} -rf %{SOURCE0}

# cleaning the build root
%clean
%{__rm} -rf %{buildroot}

# The list of files that will be installed in the end user's system.
%files
%defattr(644,ifauser,ifauser,755)
/var/app/data/nginx/html/ui/*

# A record of changes that have happened to the package between different Version or Release builds.
%changelog
