package br.com.dev.projeto.imagine.domain;

/**
 * Created by cedrim on 12/09/16.
 */
public class ImagineService
{


  /*  private static List<Imagine> parseJson(Context context, String json) throws IOException {
        List<Imagine> mecanicas = new ArrayList<>();

        try {

            JSONObject root = new JSONObject(json);
            JSONObject object = root.getJSONObject("carros");
            JSONArray jsonCarros = object.getJSONArray("carro");

            for (int i = 0; i < jsonCarros.length(); i++) {
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Imagine mecanica = new Imagine();

                mecanica.setNome(jsonCarro.optString("nome"));
                mecanica.setUrlFoto(jsonCarro.optString("url_foto"));

                if (LOG_ON) {
                    Log.e(TAG, mecanicas.size() + "encontrados.");
                }
                mecanicas.add(mecanica);
            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return mecanicas;
    }*/

         /*
            for (int i = 0; i < 20; i++) {
                Imagine mecanica = new Imagine();

                mecanica.setNome("Nome Test " + i);
                mecanica.setDesc("Desc Test " + i);
                mecanica.setUrlFoto("http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png");

                mecanicas.add(mecanica);
            */
/*
    TextView mTxtDisplay;
    ImageView mImageView;
    mTxtDisplay = (TextView) findViewById(R.id.txtDisplay);
    String url = "http://my-json-feed";

    JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    mTxtDisplay.setText("Response: " + response.toString());
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub

                }
            });

// Access the RequestQueue through your singleton class.
    MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
*/

}









