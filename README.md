Dropwizard Honeybadger
===============

Addon for Dropwizard adding support for logging to [honeybadger](https://www.honeybadger.io).


Usage
-----

The Dropwizard honeybadger provides an `AppenderFactory` which is automatically registered in Dropwizard and will send log
messages directly to your configured honeybadger account.


Configuration
-------------

The Logback Honeybadger appender can be configured using the attributes outlined in
[honeybadger-logback-appender README](https://github.com/Workable/honeybadger-java/blob/master/honeybadger-logback-appender/README.md).

Your YAML configuration could include the following snippet to configure the `HoneybadgerAppender`:

    appenders:
        - type: console
          threshold: DEBUG
          target: stderr
        - type: honeybadger
          threshold: INFO
          apiKey: xxxxx
          # async: true
          # maxThreads: 1
          # queuesize: 10
          # priority: 1

Properties
----------

* **apiKey**: Specify the apiKey from your Honeybadger account
* **async**: The error dispatching to honebadger.io is performed asynchronously via http in order to avoid performance impact. Defaults to true;
* **maxThreads**: By default the thread pool used for async dispatching contains one thread per processor available to the JVM. It's possible to manually set the number of threads (for example if you want only one thread) with the option `maxThreads`:
* **queuesize**: The default queue used to store the not yet processed events doesn't have a limit. Depending on the environment (if the memory is sparse) it is important to be able to control the size of that queue to avoid memory issues.
* **priority**: As in most cases sending error to Honebadger isn't as important as an application running smoothly, the threads have a [minimal priority](http://docs.oracle.com/javase/6/docs/api/java/lang/Thread.html#MIN_PRIORITY).

Contributors
------------

* [Nikolaos Dimos](https://github.com/nikosd23)