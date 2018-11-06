package hu.szte.bookstore.controller;

import com.google.gson.Gson;

/**
 *
 * Kozos parent a controllerekhez
 * @author Botond
 */
public class BaseController {

    String createGsonFromObject(final Object object) {
        return new Gson().toJson(object);
    }

}
