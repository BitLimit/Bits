package com.bitlimit.bits.persistence.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/21/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */

@BelongsToParents({@BelongsTo(parent = PlayerServerRecord.class, foreignKeyName = "id_player_server_records")})
public class PlayerStatistic extends Model
{
}
