import Foundation

@objc public class jitCall: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
