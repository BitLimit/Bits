package com.bitlimit.bits.persistence.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/23/13
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */

@BelongsTo(parent = Market.class, foreignKeyName = "id_markets")
public class DemandLevel extends Model
{
}
