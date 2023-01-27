# Change Log
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) 
and this project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]
### Added

### Changed
- Nothing yet

## [0.0.3] - 2022-01-26
### Added
- Nothing yet

### Changed
- The project updated to Spring Boot 3.x
- Move from Java 1.8 to Java 17
- Drop org.pegdown.pegdown dependency since it doesn't have support for Java 17
- Some stylistic changed according to IDE inspection
- Added GitHub Actions for CI/CD purposes

## [0.0.2] - 2016-10-22
### Added
- Support for getting git history as html, json or md formatted report
- Raw CHANGELOG.MD can be fethched with request param raw=true

## [0.0.1] - 2016-10-22
### Added
- Changelog file loader
- Changelog to html conversion
- Endpoint to show current changelog
