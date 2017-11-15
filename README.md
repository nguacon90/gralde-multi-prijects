# gralde-multi-projects
Sample of multi modules in gradle project.
* Simple project run with multiple servlet application context.
* All configuration about applicationContext, servlet dynamically
# Strategy
* properties file of each module has pattern: `<module>-application.properties`
* application context xml configuration has pattern: `<module>-applicationContext.xml`
* Add custom Properties listener to load properties file: `com.nguacon.platform.spring.listener.PropertyFilePatternRegisteringListener`
* Register custom listener in `/META-INF/spring.factories`
* Custom configure dynamic DispatcherSeverlet: `com.nguacon.platform.servlet.ServletConfig`
# sample/build.gradle
* Sample for build executable jar for multiple subprojects with gradle
