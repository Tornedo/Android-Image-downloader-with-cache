
# Android-Image-and-json-downloader-
this library will download the image and json and cache the result using volley
# Demo
![screen shot 2019-01-07 at 6 12 43 pm](https://user-images.githubusercontent.com/7686968/50767673-e6a62f80-12a7-11e9-88e1-66037714c1de.png)

# How to use
```javascript
 MVFetcher.json(MvApplication.getContext())
                .loadJsonArray(BASE_URL, ImageListResponse.class, new ResponseListener<List<ImageListResponse>>() {

                    @Override
                    public void onSuccess(List<ImageListResponse> response) {
                       //Your code here
                    }

                    @Override
                    public void onFailed(String cause) {
                        //handle the error
                    }
                });
```

# Technologies and Libraries
* MVP
* Clean Architecture
* volley
* Gson
* Junit

# License
Copyright 2018, Tornedo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
