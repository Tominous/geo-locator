# geo-locator
This plugin provides the possibility to find out where players live. It also includes an api.

## How to use the api

You can access the api trough the GeoIpPlugin class.

```
final GeoIpApi api = GeoIpPlugin.getPlugin(GeoIpPlugin.class).getInjector().getInstance(GeoIpApi.class);
```
Now you use the methods of the api.
