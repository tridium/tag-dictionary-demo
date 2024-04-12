/*
 * Copyright 2024 Tridium, Inc. All Rights Reserved.
 */

package com.tagdictionary.themepark;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.baja.control.BControlPoint;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.tag.BasicRelation;
import javax.baja.tag.Entity;
import javax.baja.tag.Id;
import javax.baja.tag.Relation;
import javax.baja.tag.Relations;
import javax.baja.tagdictionary.BRelationInfo;
import javax.baja.tagdictionary.BTagDictionaryService;

import com.tridium.sys.tag.ComponentRelations;

@NiagaraType
public class BVenueSeatRelation
  extends BRelationInfo
{
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tagdictionary.themepark.BVenueSeatRelation(2979906276)1.0$ @*/
/* Generated Wed Mar 20 13:26:00 EDT 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BVenueSeatRelation.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public BVenueSeatRelation()
  {
  }

  /**
   * Add a hasSeat relation if this Entity has an incoming Venue relation.; see
   * {@link #getRelation(Entity)}.
   *
   * @param source    The entity for the relations.
   * @param relations A collection of relations that are implied on the given entity.
   */
  @Override
  public void addRelations(Entity source, Collection<Relation> relations)
  {
    // The source must be a component with nav children
    if (!(source instanceof BComponent))
    {
      return;
    }
    BComponent component = (BComponent) source;
    if (!component.hasNavChildren())
    {
      return;
    }

    // Check direct relations for inbound hasVenue
    Id hasVenueId = Id.newId("thmpk", "hasVenue");
    Relations directRelations = new ComponentRelations(component);
    for (Relation relation : directRelations.getAll(hasVenueId))
    {
      if (relation.isInbound())
      {
        addImpliedSeatRelations(component, relations, directRelations);
      }
    }
    // Check for implied relations for inbound hasVenue
    BTagDictionaryService tagDictionaryService =
      (BTagDictionaryService) Sys.getService(BTagDictionaryService.TYPE);
    Optional<Relation> optionalRelation =
      tagDictionaryService.getImpliedRelation(hasVenueId, source);
    if (optionalRelation.isPresent() &&
      optionalRelation.get().isInbound())
    {
      addImpliedSeatRelations(component, relations, directRelations);
    }
  }

  /**
   * Get the first instance of a hasSeat relation.
   *
   * @param source The entity for the relation.
   * @return Returns an inverse relation with the same endpoints but in the opposite direction,
   * or Optional.empty() if the inverse relation cannot be resolved.
   */
  @Override
  public Optional<Relation> getRelation(Entity source)
  {
    ArrayList<Relation> relations = new ArrayList<>();
    addRelations(source, relations);
    if (relations.isEmpty())
    {
      return Optional.empty();
    }

    return Optional.of(relations.get(0));
  }

  private void addImpliedSeatRelations(BComponent component,
    Collection<Relation> relations, Relations directRelations)
  {
    Id hasSeatId = Id.newId("thmpk", "hasSeat");
    Id seat = Id.newId("thmpk", "seat");
    for (BComponent child : component.getChildComponents())
    {
      // Skip if there is a direct hasSeat relation
      if (directRelations.get(hasSeatId, child, Relations.OUT).isPresent())
      {
        continue;
      }

      // Add hasSeat to ControlPoint child that has a seat tag
      if ((child instanceof BControlPoint) && child.tags().contains(seat))
      {
        relations.add(new BasicRelation(hasSeatId, child, Relation.OUTBOUND));
      }
    }
  }
}
