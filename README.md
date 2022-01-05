# Maven Project Template for JHotDraw applications

## Known issues

### Missing resources

When the application is launched, it will complain in the logs about some missing resources, which can be ignored.

```
Warning ResourceBundleUtil[org.jhotdraw.draw.Labels].getIconProperty "view.increaseHandleDetailLevel.smallIcon" not found.
```

### CRC corruption error

When the application is launched, it will show the following error in the logs:

```
sun.awt.image.PNGImageDecoder$PNGException: crc corruption
```

Reason for this has not been investigated, but everything seems to work fine anyway.