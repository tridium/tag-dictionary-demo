/*
 * Copyright 2024 Tridium, Inc. All Rights Reserved.
 */

package com.tagdictionary.themepark;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.baja.control.BControlPoint;
import javax.baja.data.BIDataValue;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.space.BComponentSpace;
import javax.baja.sys.BComponent;
import javax.baja.sys.BStation;
import javax.baja.sys.BString;
import javax.baja.tag.Entity;
import javax.baja.tag.Tag;
import javax.baja.tagdictionary.BTagInfo;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridium.tagdictionary.condition.BIsTypeCondition;

@NiagaraType
@NiagaraProperty(
  name = "validity",
  type = "BTagRuleCondition",
  defaultValue = "new BIsTypeCondition(BControlPoint.TYPE)",
  override = true
)
public class BIdTag
  extends BTagInfo
{
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tagdictionary.themepark.BIdTag(1241519118)1.0$ @*/
/* Generated Wed Mar 20 13:26:00 EDT 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Property "validity"

  /**
   * Slot for the {@code validity} property.
   * @see #getValidity
   * @see #setValidity
   */
  public static final Property validity = newProperty(0, new BIsTypeCondition(BControlPoint.TYPE), null);

  //endregion Property "validity"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BIdTag.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public BIDataValue getDefaultValue()
  {
    return BString.DEFAULT;
  }

  /**
   * Add a smart tag that defines an ID string containing a Type 3 UUID based on the NSpace ord.
   *
   * @param entity    The entity for the tag.
   */
  @Override
  public Tag getTag(Entity entity)
  {
    // Check if this is a control point
    if (!(entity instanceof BControlPoint))
    {
      return null;
    }

    BComponent component = (BComponent)entity;
    BComponentSpace space = component.getComponentSpace();
    if (space == null)
    {
      return null;
    }

    BComponent root = space.getRootComponent();
    if (!(root instanceof BStation))
    {
      return null;
    }

    String stationName = ((BStation)root).getStationName();
    String handleOrd = component.getHandleOrd().encodeToString();
    int idLength = stationName.length() + /*:*/ 1 + handleOrd.length();
    StringBuilder id = new StringBuilder(idLength)
      .append(stationName)
      .append(":")
      .append(handleOrd);

    return new Tag(getTagId(), BString.make(UUID.nameUUIDFromBytes(
      id.toString().getBytes(StandardCharsets.UTF_8)).toString()));
  }
}
