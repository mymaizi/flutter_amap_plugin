#import "MyamapPlugin.h"
#import <myamap/myamap-Swift.h>

@implementation MyamapPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftMyamapPlugin registerWithRegistrar:registrar];
}
@end
