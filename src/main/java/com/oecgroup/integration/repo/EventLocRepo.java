package com.oecgroup.integration.repo;

import com.oecgroup.integration.model.EventLocMap;
import java.util.Map;

/**
 * Created by Allis Kuo on 2019-03-22
 */
public class EventLocRepo {

  private Map<String, EventLocMap> inMemCombinedKeyMap;

  public EventLocMap findByCombinedKey(String composKey) {
    return inMemCombinedKeyMap.get(composKey);
  }
}
