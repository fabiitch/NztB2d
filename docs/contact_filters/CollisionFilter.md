Box2D Tutorial: Collision filtering
===================================
[original article](https://www.aurelienribon.com/post/2011-07-box2d-tutorial-collision-filtering)

LibGdx code : 
````java
private boolean contactFilter (long fixtureA, long fixtureB) {
		if (contactFilter != null)
			return contactFilter.shouldCollide(fixtures.get(fixtureA), fixtures.get(fixtureB));
		else {
			Filter filterA = fixtures.get(fixtureA).getFilterData();
			Filter filterB = fixtures.get(fixtureB).getFilterData();

			if (filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != 0) {
				return filterA.groupIndex > 0;
			}

			boolean collide = (filterA.maskBits & filterB.categoryBits) != 0 && (filterA.categoryBits & filterB.maskBits) != 0;
			return collide;
		}
	}
````

Box2D C++ Function ShouldCollide
--------------------

```cpp
bool b2ContactFilter::ShouldCollide(b2Fixture* fixtureA, b2Fixture* fixtureB)
{
    const b2Filter& filterA = fixtureA->GetFilterData();
    const b2Filter& filterB = fixtureB->GetFilterData();

    if (filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != 0)
    {
        return filterA.groupIndex > 0;
    }

    bool collideA = (filterA.maskBits & filterB.categoryBits) != 0;
    bool collideB = (filterA.categoryBits & filterB.maskBits) != 0
    bool collide =  collideA && collideB;
    return collide;
}
```

Today I want to make a tutorial about collision filtering in the Box2D engine, because it is something that is not that
easy to master, and yet it is a very powerful and useful feature.

_Note: examples and source code are related to the [LibGDX](http://libgdx.badlogicgames.com/) (Android) implementation
of the Box2D API, but the concepts are the same for any other implementation, only the syntax may differ._

There are two ways to deal with collision filtering: by using **categories and masks**, or by using **groups**. These
parameters can be found in
the [Filter](http://code.google.com/p/libgdx/source/browse/trunk/gdx/src/com/badlogic/gdx/physics/box2d/Filter.java)
component of
a [Fixture](http://code.google.com/p/libgdx/source/browse/trunk/gdx/src/com/badlogic/gdx/physics/box2d/Fixture.java).
Filtering collisions with any of these attributes will assure you that collision checks between two bodies that should
not collide with each other will not be evaluated at all, resulting in a gain of speed.

Illustration example
--------------------

To illustrate collision filtering, let's take an example: we have three kinds of objects in a simple platformer game: **
players**, **monsters**, and **scenery**.

We want the following rules: players should not collide with each others, neither do monsters, but players should
collide with monsters (and vice-versa). Of course, players and monsters have to collide with the scenery. Scenery object
will collide with each other (to build pyramids of crates for instance).

![alt text](box2d-tutorial-collision-filtering-1.jpg "Title")

Filter categories and masks
---------------------------

Categories and masks are the most powerful way to deal with collision filtering, but also the most complicated for
newcomers. The idea is to define a category per object type, and to use the masks to filter collisions between these
object types.

Therefore, we start be defining three categories:

    final short CATEGORY_PLAYER = 0x0001;  // 0000000000000001 in binary
    final short CATEGORY_MONSTER = 0x0002; // 0000000000000010 in binary
    final short CATEGORY_SCENERY = 0x0004; // 0000000000000100 in binary

Then we can assign these categories to our objects:

    player1FixtureDef.filter.categoryBits = CATEGORY_PLAYER;
    player2FixtureDef.filter.categoryBits = CATEGORY_PLAYER;
    
    monster1FixtureDef.filter.categoryBits = CATEGORY_MONSTER;
    monster2FixtureDef.filter.categoryBits = CATEGORY_MONSTER;
    monster3FixtureDef.filter.categoryBits = CATEGORY_MONSTER;
    monster4FixtureDef.filter.categoryBits = CATEGORY_MONSTER;
    
    groundFixtureDef.filter.categoryBits = CATEGORY_SCENERY;
    crate1FixtureDef.filter.categoryBits = CATEGORY_SCENERY;
    crate2FixtureDef.filter.categoryBits = CATEGORY_SCENERY;

Wait, _0x001_, _0x002_ and _0x004_ ? Why not 1, 2 and 3 ? Because Box2D categories (and masks)
are [bit fields](http://en.wikipedia.org/wiki/Bit_field) (coded as shorts, thus on 16 bits) ! That means that the
possible categories are powers of 2 (in decimal: 1, 2, 4, 8, 16, 32, 64, 128..., or in hexadecimal: 0x1, 0x2, 0x4, 0x8,
0x10, 0x20, 0x40, 0x80...). 16 bits means that there are 16 categories possible, from 0x0001 to 0x8000.

Now, let's define the filter masks. Masks work as follows:

    for each object o1 in the world
        for each object o2 in the world
            isCollisionEnabled = (o1.filter.maskBits & o2.filter.collisionBits) ≠ 0

Can you guess the resulting masks in our game ? They are as follows:

    final short MASK_PLAYER = CATEGORY_MONSTER | CATEGORY_SCENERY; // or ~CATEGORY_PLAYER
    final short MASK_MONSTER = CATEGORY_PLAYER | CATEGORY_SCENERY; // or ~CATEGORY_MONSTER
    final short MASK_SCENERY = -1;

The "~" in front of a category name is a one's complement in C family languages (including Java). For instance, ~0x0001
= 0xFFFE. In binary, it changes 0 into 1 and vice-versa.

Since the scenery category has to collide with everything, we just set its mask to -1, which also equals to 0xFFFF in a
bit field (its due to the representation of signed numbers).

> Using a _0xFFFF_ (or -1) mask will enable collisions with every category. As a contrary, setting a mask to _0x0000_ (or 0) will disable collisions with everything !

We can assign these masks to our objects:

    player1FixtureDef.filter.maskBits = MASK_PLAYER;
    player2FixtureDef.filter.maskBits = MASK_PLAYER;
    
    monster1FixtureDef.filter.maskBits = MASK_MONSTER;
    monster2FixtureDef.filter.maskBits = MASK_MONSTER;
    monster3FixtureDef.filter.maskBits = MASK_MONSTER;
    monster4FixtureDef.filter.maskBits = MASK_MONSTER;
    
    groundFixtureDef.filter.maskBits = MASK_SCENERY;
    crate1FixtureDef.filter.maskBits = MASK_SCENERY;
    crate2FixtureDef.filter.maskBits = MASK_SCENERY;

That's all. Just let the magic of Box2D operates.

Filter groups
-------------

You can do everything you need with categories and masks, but sometimes you don't need their bit-field complexity for
some tasks. Groups were specifically introduced to quickly disable or enable collision checks between objects that are
somehow related. The Box2D manual says:

> Collision groups let you specify an integral group index. You can have all fixtures with the same group index always collide (positive index) or never collide (negative index).

Groups should be used instead of categories and masks when you only need to disable collisions between objects of a same
category. Therefore, groups can be used in our illustration example, because everything should collide with everything,
except players against players and monsters against monsters. We can shorten the previous setup to:

    final short GROUP_PLAYER = -1;
    final short GROUP_MONSTER = -2;
    final short GROUP_SCENERY = 1;

And we assign these groups as follows:

    player1FixtureDef.filter.groupIndex = GROUP_PLAYER;
    player2FixtureDef.filter.groupIndex = GROUP_PLAYER;
    
    monster1FixtureDef.filter.groupIndex = GROUP_MONSTER;
    monster2FixtureDef.filter.groupIndex = GROUP_MONSTER;
    monster3FixtureDef.filter.groupIndex = GROUP_MONSTER;
    monster4FixtureDef.filter.groupIndex = GROUP_MONSTER;
    
    groundFixtureDef.filter.groupIndex = GROUP_SCENERY;
    crate1FixtureDef.filter.groupIndex = GROUP_SCENERY;
    crate2FixtureDef.filter.groupIndex = GROUP_SCENERY;

That's all. That's two times less code than with categories and masks. However, there is no way to disable collisions
between groups. That's why categories and masks are more powerful than groups.

Conclusion
----------

Collision filters are very valuable tools. Use them with care but use them everywhere. Collisions can also be disabled
in the world contact listener, but collisions have to be checked first before a contact is reported, so this kind of
filtering won't remove the computational overhead of the collision check. Go the fixture filter way, always !
