package com.sbisec.helios.ap.common.util;

public class CustomNamingStrategy
{
	public static final com.fasterxml.jackson.databind.PropertyNamingStrategy SANE_SNAKE_CASE = new SaneSnakeCaseStrategy();

	@SuppressWarnings("serial")
	public static class SaneSnakeCaseStrategy extends com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase
	{
		@Override
		public String translate(String input)
		{
			if(input == null) return input; // garbage in, garbage out
			int length = input.length();
			StringBuilder result = new StringBuilder(length * 2);
			int resultLength = 0;
			
			@SuppressWarnings("unused")
			boolean wasPrevTranslated = false;
			for(int i = 0; i < length; i++)
			{
				char c = input.charAt(i);
				if(i > 0 || c != '_') // skip first starting underscore
				{
					if(Character.isUpperCase(c))
					{
						if(resultLength > 0)
						{
							result.append('_');
							resultLength++;
						}
						c = Character.toLowerCase(c);
						wasPrevTranslated = true;
					}
					else
					{
						wasPrevTranslated = false;
					}
					result.append(c);
					resultLength++;
				}
			}
			return resultLength > 0 ? result.toString() : input;
		}
	}
}
