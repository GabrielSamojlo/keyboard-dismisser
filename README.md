# Keyboard Dismisser

### What is this?

Keyboard Dismisser is a simple library that allows you to dismiss keyboard by tapping anywhere outside it.

Currently supported root layouts of activities or fragments are : CoordinatorLayout, LinearLayout and RelativeLayout.

![Demo](http://i.giphy.com/l3vRavNL0aQfL2tva.gif)

### How to download?

Add Keyboard Dismisser as a dependency in your app level ```build.gradle``` file.

```gradle
dependencies {
    compile 'com.gabrielsamojlo.keyboarddismisser:keyboard-dismisser:1.0.1'
}

```

You can now use Keyboard Dismisser!

### How to use?

Keyboard Dismisser works on whole Activity of your application or on specific Fragment.
To use it with Activity, you just need to inform Keyboard Dismisser that you want to use it in your ```onCreate``` method :

```java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KeyboardDismisser.useWith(this);
    }
```

For fragments, you should place it in ```onViewCreated``` :

```java
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KeyboardDismisser.useWith(this);
    }
```

### Support, contact and contribution

Feel free to contact me at gabrielsamojlo@gmail.com for any questions.
Any forms of contribution are welcome, so feel free to fork and contribute with pull requests.

### License
```
Copyright 2016 Gabriel Samojło

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
