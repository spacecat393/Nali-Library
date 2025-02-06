# Build
<span style="font-size: large; ">

- Set up Nali [TemplateDevEnv](https://github.com/CleanroomMC/TemplateDevEnv)
- When writing Mixins on IntelliJ, it is advisable to use latest [MinecraftDev Fork for RetroFuturaGradle](https://github.com/eigenraven/MinecraftDev/releases)
>gradle.properties
```properties
mod_version = 2.2.0
root_package = com.nali
mod_id = nali
mod_name = Nali

use_mixins = true

is_coremod = true
coremod_plugin_class_name = com.nali.${mod_id}.Core
```
>[Code](doc/code.md)

>[Resource](doc/resource.md)

</span>