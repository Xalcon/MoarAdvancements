# <span style="color:red">THIS PROJECT HAS BEEN MERGED INTO FORGE!</span>
As of version 1.12 - 14.21.1.2436, this feature is now included in forge. This mod will no longer work
properly or even cause crashes. This project will only stay up as a reference for other modders.

# MoarAdvancements
Replaces the advancements screen with a new gui that supports multiple pages for advancements

## Why?
The vanilla advancement screen only supports 26 advancement tabs. Thats why :P

## Disclaimer
This mod consists of 2 parts that are bundled into one jar. The biggest part is a coremod which is getting assisted by a really small FML mod.
This means, the mod is currently not compliant to the Forge Coremod Policy! I will work on that as soon as possible, but at the moment, forge does not
support loading coremods from embedded jars and I dont want to ship the coremod + a single classfile mod separately.

## How does it work?
This is a coremod(😱) that modifies the GuiScreenAdvancements class:
* Adds a public integer field `selectedPage` to the class which can be set from outside sources (in this case a GuiEvent handler)
* Patches the `rootAdvancementAdded` method to call `GuiAdvancementsTabEx.createEx()` instead of `GuiAdvancementsTab.create()` which handles the
calculation of the page and index on the page
* Patches the `drawScreen()` method to call `super.drawScreen()` to allow the use of buttons on the Gui

The rest of the logic is handled by the `GuiAdvancementsTabEx` which just extends `GuiAdvancementsTab` and adds some paging logic.
Rendering the paging buttons as well as the switching logic is handled in the `GuiEventHandlerAdvancementScreen`, so I dont have to add more ASM
patches to the GuiScreenAdvancements class :)~~