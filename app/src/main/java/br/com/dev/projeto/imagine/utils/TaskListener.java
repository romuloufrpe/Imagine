package br.com.dev.projeto.imagine.utils;

/**
 * Created by cedrim on 15/09/16.
 */
public interface TaskListener<T> {
    //Executa background em Thread e retorna o objeto
    T execute() throws Exception;

    //Atualiza a View
    void updateView(T response);

    //caso o execute de erro ele é chamado
    void onError(Exception exception);

    //caso cancelada
    void onCacelled(String cod);
}
