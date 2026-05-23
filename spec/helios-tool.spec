# This spec file is for creating RPM.

%define name helios-tool
%define version 1.4.4
%define release 1
%define _binaries_in_noarch_packages_terminate_build 0
%define __jar_repack %{nil}

Name:       %{name}
Version:    %{version}
Release:    %{release}
Summary:    Helios Tool
License:    Proprietary
Source0:    ifa-portal-tool
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
mkdir -p %{buildroot}/opt/ifauser/ifa-portal-tool
cp -rp %{SOURCE0}/* %{buildroot}/opt/ifauser/ifa-portal-tool/
%{__rm} -rf %{SOURCE0}

# cleaning the build root
%clean
%{__rm} -rf %{buildroot}

# The list of files that will be installed in the end user's system.
%files
%defattr(644,ifauser,ifauser,755)
%attr(744,ifauser,ifauser) /opt/ifauser/ifa-portal-tool/InitRedisCache.sh
/opt/ifauser/ifa-portal-tool/*

# A record of changes that have happened to the package between different Version or Release builds.
%changelog
