import { PluginListenerHandle } from '@capacitor/core';

export interface JitCallPlugin {

	initialize(): Promise<{ token: string }>;
	startCall(options: { meetingId: string; name: string }): Promise<void>;
	addListener(

		eventName: string | undefined,
		listenerFunc: (data: { meetingId: string; userFrom: string }) => void

	): Promise<PluginListenerHandle>;

}

declare module '@capacitor/core'{

	interface PluginRegistry {

		JitCall: JitCallPlugin;

	}

}