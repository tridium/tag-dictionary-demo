/*
 * Copyright 2024 Tridium, Inc. All Rights Reserved.
 */

package com.tagdictionary.themepark;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BObject;
import javax.baja.sys.Type;
import javax.baja.tag.Entity;
import javax.baja.tagdictionary.BTagRuleCondition;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;

import com.tridium.json.JSONObject;
import com.tridium.json.JSONWriter;

@NiagaraType
@NiagaraProperty(
  name = "startsWith",
  type = "String",
  defaultValue = ""
)
@NiagaraProperty(
  name = "endsWith",
  type = "String",
  defaultValue = ""
)
public class BIsNamedWith
  extends BTagRuleCondition
{
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tagdictionary.themepark.BIsNamedWith(4212788859)1.0$ @*/
/* Generated Wed Mar 20 08:42:38 EDT 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Property "startsWith"

  /**
   * Slot for the {@code startsWith} property.
   * @see #getStartsWith
   * @see #setStartsWith
   */
  public static final Property startsWith = newProperty(0, "", null);

  /**
   * Get the {@code startsWith} property.
   * @see #startsWith
   */
  public String getStartsWith() { return getString(startsWith); }

  /**
   * Set the {@code startsWith} property.
   * @see #startsWith
   */
  public void setStartsWith(String v) { setString(startsWith, v, null); }

  //endregion Property "startsWith"

  //region Property "endsWith"

  /**
   * Slot for the {@code endsWith} property.
   * @see #getEndsWith
   * @see #setEndsWith
   */
  public static final Property endsWith = newProperty(0, "", null);

  /**
   * Get the {@code endsWith} property.
   * @see #endsWith
   */
  public String getEndsWith() { return getString(endsWith); }

  /**
   * Set the {@code endsWith} property.
   * @see #endsWith
   */
  public void setEndsWith(String v) { setString(endsWith, v, null); }

  //endregion Property "endsWith"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BIsNamedWith.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  @Override
  public boolean test(Entity entity)
  {
    if (!(entity instanceof BObject))
    {
      return false;
    }

    if (!getStartsWith().isEmpty() && ((BObject)entity).asComplex().getName().startsWith(getStartsWith()))
    {
      return true;
    }
    if (!getEndsWith().isEmpty() && ((BObject)entity).asComplex().getName().endsWith(getEndsWith()))
    {
      return true;
    }

    return false;
  }

  @Override
  public boolean testIdealMatch(Type type)
  {
    return false;
  }

  @Override
  public void encodeToJson(JSONWriter writer) {
    // In many cases you will need to call super.encodeToJson(writer);
    writer.key(startsWith.getName()).value(getStartsWith());
    writer.key(endsWith.getName()).value(getEndsWith());
  }

  @Override
  public void decodeFromJson(JSONObject conditionJson) {
    // In many cases you will need to call super.decodeFromJson(conditionJson);
    setStartsWith(conditionJson.getString(startsWith.getName()));
    setEndsWith(conditionJson.getString(endsWith.getName()));
  }
}
