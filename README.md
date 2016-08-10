# moezbhatti/recycler-adapter

Forget the boilerplate that comes with writing Adapters. `recyclerview-adapter` is very small and lightweight library to set up `RecyclerViews` quickly and easily

Just by setting the data type that you're representing in your list, as a Type argument when creating your adapter, the `MBAdapter` will create and maintain a list to hold your data for you, and automatically update the `RecyclerView` when you add/remove/replace data. It will also keep track of a `selected` state (useful when implementing multi-selection), and re-bind the `MBViewHolder` whenever the state changes

## Usage

Create a class for your ViewHolder

``` java
public class SimpleViewHolder extends MBViewHolder<DataType> {

    // Declare your views
    protected TextView mTextView;
    ...
    
    public class SimpleViewHolder(MBAdapter<DataType> adapter, View view) {
        super(adapter, view);

        // Find your views. I highly recommend using ButterKnife instead of doing it this way
        mTextView = (TextView) view.findViewById(R.id.text);
        ...
    }
    
    @Override
    public void bind(int position) {
        // Get your data object from the adapter
        DataType data = mAdapter.getItem(position);
        
        // Now bind data to your views
        mTextView.setText(data.getName());
        ...
    }
}

```

Now, you just need to create a simple class for your adapter

``` java
public class SimpleAdapter extends MBAdapter<DataType> {

    public SimpleAdapter(Context context) {
        super(context);
    }
    
    @Override
    public MBViewHolder<DataType> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        // Inflate your view
        View view = inflater.inflate(R.layout.list_item_simple, parent, false);
        
        // Return a new instance of your ViewHolder
        return new SimpleViewHolder(this, view);
    }

}
```

Now you can use it with your `RecyclerView`

``` java
SimpleAdapter adapter = new SimpleAdapter(mContext);

mRecyclerView.setAdapter(adapter);
```

And here are some of the methods that you can use without having to write all of the usual boilerplate
``` java
setItems(ArrayList<DataType> data);

void addItems(ArrayList<DataType> data);

void addItem(DataType data);

void addItem(int position, DataType data);

void replaceItem(int position, DataType data);

void removeItem(DataType data);

void removeItem(int position);

void clear();

void setSelected(int position);

boolean isSelected(int position);

DataType getItem(int position);

ArrayList<DataType> getItems();

```

## To-do
 - Support for `onClick` and `onLongClick` at the Adapter level. I personally don't use these methods like this, but it's nice functionality to have for people who do
 - Support for multiple view types in a single adapter
 - Support for easily adding headers and footers
 - If you have any suggestions, create an issue and I'll definitely take them into consideration!


## Installation

**Gradle**

``` gradle
repositories {
  maven { url "https://jitpack.io" }
}
```

``` gradle
dependencies {
  compile 'com.github.moezbhatti:recycler-adapter:0.1.2'
}
```

**Maven**
``` xml
<repositories>
  	<repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
	</repository>
</repositories>
```

``` xml
<dependency>
    <groupId>com.github.moezbhatti</groupId>
    <artifactId>recycler-adapter</artifactId>
    <version>0.1.2</version>
</dependency>
```

## License

```
Copyright 2016 Moez Bhatti

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
