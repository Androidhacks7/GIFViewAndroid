# GIFViewAndroid

An ImageView in android with support for rendering GIF Images

Sample:

XML:

<com.androidhacks7.GIFViewAndroid
  android:id="@+id/your_gif_view" />


Java:
GIFViewAndroid gifViewAndroid = (GIFViewAndroid) findViewById(R.id.your_gif_view);
gifViewAndroid.setImageResource(R.drawable.your_gif);

Automatically scales to the size mentioned in attributes
