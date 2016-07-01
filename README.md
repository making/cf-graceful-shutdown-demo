# cf-graceful-shutdown-demo


``` bash
$ cf push
$ ab -n 1000 -c 20 http://demo.local.pcfdev.io/
```

at another terminal

``` bash
$ cf zero-downtime-push demo -f manifest.yml
```


`cf logs` in old version will look like:

```
2016-07-01T18:41:49.18+0900 [APP/0]      OUT 2016-07-01 09:41:49.180  INFO 15 --- [       Thread-3] ationConfigEmbeddedWebApplicationContext : Closing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@18cb7620: startup date [Fri Jul 01 09:37:41 UTC 2016]; root of context hierarchy
2016-07-01T18:41:49.19+0900 [APP/0]      OUT 2016-07-01 09:41:49.191  INFO 15 --- [       Thread-3] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 0
2016-07-01T18:41:49.19+0900 [API/0]      OUT Deleted app with guid e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:49.20+0900 [APP/0]      OUT 2016-07-01 09:41:49.201  INFO 15 --- [       Thread-3] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
2016-07-01T18:41:49.20+0900 [CELL/0]     OUT Exit status 0
2016-07-01T18:41:51.36+0900 [APP/0]      OUT 2016-07-01 09:41:51.364  INFO 15 --- [ost-startStop-2] com.example.GracefulShutdownFilter       : Destroy -> 10
2016-07-01T18:41:51.36+0900 [APP/0]      OUT 2016-07-01 09:41:51.366  INFO 15 --- [ost-startStop-2] com.example.GracefulShutdownFilter       : 10 requests remains.
2016-07-01T18:41:51.86+0900 [APP/0]      OUT 2016-07-01 09:41:51.862  INFO 15 --- [io-8080-exec-17] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.86+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.824 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55918 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:0d9ff62f-06d7-4020-5b9c-3e9f7f681ec3 response_time:5.041493494 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.88+0900 [APP/0]      OUT 2016-07-01 09:41:51.880  INFO 15 --- [io-8080-exec-19] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.88+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.848 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55922 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:c7237b73-9d3d-4917-7389-fa03ee2b872e response_time:5.03511538 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.89+0900 [APP/0]      OUT 2016-07-01 09:41:51.893  INFO 15 --- [io-8080-exec-22] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.89+0900 [APP/0]      OUT 2016-07-01 09:41:51.895  INFO 15 --- [nio-8080-exec-5] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.89+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.864 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55923 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:f838b21c-5e0f-42b3-6be8-abeff53fb55f response_time:5.031598661 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.90+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.890 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55931 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:ca7483dd-0ebc-4c1d-7530-f1fde652816a response_time:5.014273152 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.90+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.876 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55930 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:1ab75711-9525-4b90-5632-51907c2bc413 response_time:5.029622655 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.90+0900 [APP/0]      OUT 2016-07-01 09:41:51.899  INFO 15 --- [nio-8080-exec-3] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.91+0900 [APP/0]      OUT 2016-07-01 09:41:51.901  INFO 15 --- [nio-8080-exec-6] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.91+0900 [APP/0]      OUT 2016-07-01 09:41:51.902  INFO 15 --- [nio-8080-exec-7] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.91+0900 [APP/0]      OUT 2016-07-01 09:41:51.905  INFO 15 --- [io-8080-exec-11] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.92+0900 [APP/0]      OUT 2016-07-01 09:41:51.917  INFO 15 --- [io-8080-exec-15] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.92+0900 [APP/0]      OUT 2016-07-01 09:41:51.918  INFO 15 --- [io-8080-exec-14] com.example.HelloCfApplication           : ====End  ====
2016-07-01T18:41:51.92+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.892 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55936 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:88227de0-df5b-46f0-661c-e3ca02ff947a response_time:5.031097481 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.92+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.862 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55927 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:28621647-fee7-4576-6b46-e23e32596cbf response_time:5.063647546 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.92+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.865 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55925 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:4de1d23d-d3d0-4d6b-5f1e-966d515136cf response_time:5.061663814 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.93+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.892 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55933 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:b960cfa4-5997-4247-7b0c-d09fddc4f73f response_time:5.038148062 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:51.93+0900 [RTR/0]      OUT demo.local.pcfdev.io - [01/07/2016:09:41:46.864 +0000] "GET / HTTP/1.0" 200 0 12 "-" "ApacheBench/2.3" 192.168.11.1:55924 x_forwarded_for:"192.168.11.1" x_forwarded_proto:"http" vcap_request_id:714982b2-89f2-4381-6ae3-76f8e11ac7ef response_time:5.069319467 app_id:e97bcfb2-bfe4-41e9-b362-22cf227466fc
2016-07-01T18:41:52.36+0900 [APP/0]      OUT 2016-07-01 09:41:52.366  INFO 15 --- [ost-startStop-2] com.example.GracefulShutdownFilter       : 0 requests remains.
2016-07-01T18:41:52.48+0900 [APP/0]      OUT Exit status 143
```
